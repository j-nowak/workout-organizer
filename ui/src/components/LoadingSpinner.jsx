import React, { Component } from "react";
 
class LoadingSpinner extends Component {
  render() {
    return (
      <div className="data-loading">
        <i className="fa fa-spinner fa-spin"></i>
      </div>
    );
  }
}
 
export default LoadingSpinner;