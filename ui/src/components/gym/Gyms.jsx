import React, { Component } from "react";
import axios from 'axios';
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';

import GymListing from "./GymListing.jsx";

export class Gyms extends Component {
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

        <Map 
            google={this.props.google}
            center={{
              lat: 50,
              lng: 20
            }}
            zoom={12}>
          {this.state.gyms.map(g =>
            <Marker
                title={g.name}
                position={{lat: g.address.coords.latitude, lng: g.address.coords.longitude}}
                key={g.id} />)}
        </Map>
      </div>
    );
  }
}
 
export default GoogleApiWrapper({
  apiKey: ('AIzaSyCXwgr7C1qhouask5gWoOXh2Gs_n8m08Ag')
})(Gyms)