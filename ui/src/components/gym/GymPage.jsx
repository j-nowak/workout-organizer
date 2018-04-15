import React, { Component } from "react";
import axios from 'axios';
import StarRatingComponent from 'react-star-rating-component';

import Gallery from "../Gallery.jsx";
import Popup from "../Popup.jsx";
import GymRating from "./GymRating.jsx";

class GymPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      gymId: this.props.match.params.gymId,
      gym: {},
      imagesData: [],
      show: false
    };
  }

  openPopup() {
    this.setState({
      show: true
    });
  }

  closePopup() {
    this.setState({
      show: false
    });
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms/" + this.state.gymId)
      .then(res => {
        const gym = res.data;
        this.setState({ gym });
      });

    axios.get("http://localhost:9000/react/gyms/" + this.state.gymId + "/images")
      .then(res => {
        const imagesData = res.data;
        this.setState({ imagesData });
      });
  }

  render() {
    const gym = this.state.gym;
    return (
      <div className="container">
        <Popup
            onClose={() => this.closePopup()}
            show={this.state.show}>
          <GymRating
            onClose={() => this.closePopup()}
            gymId={this.state.gymId} />
        </Popup>

        <h2>{gym.name}</h2>
        <div>
          <StarRatingComponent
            name='' 
            starCount={10}
            value={gym.rating}
            editing={false} />
          <button className="btn" onClick={() => this.openPopup()}>Add rating</button>
        </div>
        
        <div>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
        <Gallery images={this.state.imagesData} />
      </div>
    );
  }
}
 
export default GymPage;