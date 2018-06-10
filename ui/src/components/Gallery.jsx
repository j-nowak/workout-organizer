import React from "react";

import "./Gallery.css";
import LoadingSpinner from "./LoadingSpinner.jsx";

function imagesLoaded(parentNode) {
  const imgElements = [...parentNode.querySelectorAll("img")];
  for (let i = 0; i < imgElements.length; i += 1) {
    const img = imgElements[i];
    if (!img.complete) {
      return false;
    }
  }
  return true;
}

class Gallery extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: props.images.length > 0,
      focusImage: null,
      show: false
    };
  }

  handleImageChange = () => {
    this.setState({
      loading: !imagesLoaded(this.galleryElement)
    });
  };

  renderImage(imageData, index) {
    const imageUrl = 'http://localhost:9000/images/' + imageData;
    return (
      <div className="col-sm-4" key={index}>
        <img
          src={imageUrl}
          className="gallery-item"
          alt={imageData}
          onLoad={this.handleImageChange}
          onError={this.handleImageChange}
        />
      </div>
    );
  }

  renderImagesRow(imagesRow, index) {
    return (
      <div className="row" key={index}>
        {imagesRow.map(this.renderImage.bind(this))}
      </div>
    );
  }

  render() {
    var galleryImages = [];
    const chunkSize = 3
    var rowId = 0;
    for (var i = 0, j = this.props.images.length; i < j; i += chunkSize) {
      galleryImages.push(this.props.images.slice(i, i + chunkSize));
    }

    return (
      <div>
        <div className="gallery" ref={element => { this.galleryElement = element }} >
          {this.state.loading ? <LoadingSpinner /> : null}
          <div className="images">
            {galleryImages.map(this.renderImagesRow.bind(this))}
          </div>
        </div>
      </div>
    );
  }
}

export default Gallery;
