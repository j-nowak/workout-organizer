import React, { Component } from "react";
import axios from 'axios';
import "./GymPage.css"

import Gallery from "../Gallery.jsx";

class GymPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      gymId: this.props.match.params.gymId,
      gym: {},
      imagesData: []
    };
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms/" + this.state.gymId)
      .then(res => {
        const gym = res.data;
        this.setState({ gym });
      });

    this.loadImages();
  }

  loadImages() {
    axios.get("http://localhost:9000/react/gyms/" + this.state.gymId + "/images")
      .then(res => {
        const imagesData = res.data;
        this.setState({ imagesData });
      });
  }

  upload(e) {
    e.preventDefault()
    console.log(e, this.fileUpload);

    const data = new FormData();
    data.append('image', this.fileUpload.files[0]);

    axios.post(`http://localhost:9000/react/gyms/${this.state.gymId}/images`, data)
    .then(() => {
      this.loadImages();
      this.fileUpload.value = null;
    })
  }

  render() {
    const gym = this.state.gym;

    return (
      <div className="container">
        <h2>{gym.name}</h2>
        <div className="gym-desciption">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
        <h3>Upload image:</h3>
        <div className="row">
          <div className="col-sm-12">
            <form onSubmit={e => this.upload(e)}>
              <input type="file" ref={e => this.fileUpload = e} style={{display: 'inline'}} />
              <input type="submit" className="btn btn-primary" value="Upload" />
            </form>
          </div>
        </div>
        <h3>Gym images:</h3>
        <Gallery images={this.state.imagesData} />
      </div>
    );
  }
}

export default GymPage;
