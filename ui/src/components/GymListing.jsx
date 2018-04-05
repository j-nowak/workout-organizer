import React, { Component } from "react";
 
class GymListing extends Component {
  render() {
    const gym = this.props.gym;
    return (
      <div className="GymListing">
        <h1>{gym.name}</h1>
        <div>City: {gym.address.city}</div>
        <div>Street: {gym.address.street}</div>
        <div>Rating: {gym.rating} ({gym.ratingsCount} votes)</div>
      </div>
    );
  }
}
 
export default GymListing;