import React, { Component } from "react";
import axios from 'axios';

import GymListing from "./GymListing.jsx";

class Gyms extends Component {
  constructor(props) {
    super(props);

    this.state = { gyms: [] };
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms")
      .then(res => {
        const gyms = res.data;
        this.setState({ gyms });
      });
  }

  render() {
    return (
      <div className="GymList">
        <ul>
          {this.state.gyms.map(g =>
            <li key={g.id}> <GymListing gym={g} /></li>)}
        </ul>
      </div>
    );
  }
}
 
export default Gyms;