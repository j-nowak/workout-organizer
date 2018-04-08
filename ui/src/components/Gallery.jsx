import React from "react";

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
      loading: true
    };
  }

  handleImageChange = () => {
    this.setState({
      loading: !imagesLoaded(this.galleryElement)
    });
  };

  renderImage(imageUrl) {
    return (
      <div>
        <img
          src={imageUrl}
          alt=''
          onLoad={this.handleImageChange}
          onError={this.handleImageChange}
        />
      </div>
    );
  }

  render() {
    return (
      <div
        className="gallery"
        ref={element => {
          this.galleryElement = element;
        }}
      >
        {this.state.loading ? <LoadingSpinner /> : null}
        <div className="images">
          {this.props.imageUrls.map(imageUrl => this.renderImage(imageUrl))}
        </div>
      </div>
    );
  }
}

export default Gallery;