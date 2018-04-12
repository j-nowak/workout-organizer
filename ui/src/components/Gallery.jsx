import React from "react";

import "./Gallery.css";
import LoadingSpinner from "./LoadingSpinner.jsx";
import Popup from "./Popup.jsx";
import ImageDetails from "./ImageDetails.jsx";

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
      loading: true,
      focusImage: null,
      show: false
    };
  }

  handleImageChange = () => {
    this.setState({
      loading: !imagesLoaded(this.galleryElement)
    });
  };

  openPopup = (imageData) => {
    this.setState({
      focusImage: imageData,
      show: true
    });
  }

  closePopup = () => {
    this.setState({
      focusImage: null,
      show: false
    });
  }

  renderImage(imageData) {
    return (
      <div key={imageData.id}>
        <img
          src={imageData.thumbnailUrl}
          className="gallery-item"
          alt={imageData.name}
          onClick={() => this.openPopup(imageData)}
          onLoad={this.handleImageChange}
          onError={this.handleImageChange}
        />
      </div>
    );
  }

  render() {
    return (
      <div>
        <Popup
            onClose={this.closePopup}
            show={this.state.show}>
          <ImageDetails
              focusImage={this.state.focusImage} />
        </Popup>

        <div
          className="gallery"
          ref={element => {
            this.galleryElement = element;
          }}
        >
          {this.state.loading ? <LoadingSpinner /> : null}
          <div className="images">
            {this.props.images.map(imageData => this.renderImage(imageData))}
          </div>
        </div>
      </div>
    );
  }
}

export default Gallery;