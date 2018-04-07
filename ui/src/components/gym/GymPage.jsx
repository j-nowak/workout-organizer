import React, { Component } from "react";
import axios from 'axios';
import StarRatingComponent from 'react-star-rating-component';

class GymPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      gymId: this.props.match.params.gymId,
      gym: {}
    };
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms/" + this.state.gymId)
      .then(res => {
        const gym = res.data;
        this.setState({ gym });
      });
  }

  render() {
    const gym = this.state.gym;
    return (
      <div>
        <h2>{gym.name}</h2>
        <StarRatingComponent 
            starCount={10}
            value={gym.rating}
            editing={false} />
        <div>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
      </div>
    );
  }
}
 
export default GymPage;