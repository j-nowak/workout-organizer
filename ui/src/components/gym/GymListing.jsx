import React, { Component } from "react";
import { Link } from 'react-router-dom';

class GymListing extends Component {
  render() {
    const gym = this.props.gym;
    const url = "/gyms/" + gym.id;

    return (
      <div className="GymListing">
        <h1>{gym.name}</h1>
        <div>City: {gym.address.city}</div>
        <div>Street: {gym.address.street}</div>
        <Link className="btn btn-primary" to={url}>See details</Link>
      </div>
    );
  }
}
 
export default GymListing;