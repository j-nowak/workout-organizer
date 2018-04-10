import React, { Component } from "react";
import axios from 'axios';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react';
import './Gyms.css';
import { Link } from 'react-router-dom';

import GymListing from "./GymListing.jsx";

export class Gyms extends Component {
  constructor(props) {
    super(props);

    this.state = { 
      gyms: [],
      currentPage: '1',
      pagesCount: 1
    };
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms?page=" + this.state.currentPage)
      .then(res => {
        const gyms = res.data;
        const pagesCount = 5;
        this.setState({ gyms, pagesCount });
      });
  }

  updatePage(ev) {
    const currentPage = ev.target.id;
    console.log('cdacsa ' + currentPage);
    this.setState({ currentPage });
  }

  render() {
    const style = {
      width: '100%',
      height: '600px'
    }

    let pageNumbers = [];
    for (let i = 1; i <= this.state.pagesCount; i++) {
      pageNumbers.push('' + i);
    }

    return (
      <div className="row gyms-listing-container">

        <div className="col-sm-9">
          <Map 
              google={this.props.google}
              style={style}
              center={{
                lat: 50.07,
                lng: 19.9
              }}
              zoom={12}>
            {this.state.gyms.map(g =>
              <Marker
                  title={g.name}
                  position={{lat: g.address.coords.latitude, lng: g.address.coords.longitude}}
                  key={g.id} />)}
          </Map>
        </div>

        <div className="col-sm-3 gyms-listing">
          <ul>
            {this.state.gyms.map(g =>
              <li key={g.id}> <GymListing gym={g} /></li>)}
          </ul>

          <ul className="pagination">
            {pageNumbers.map(p =>
              <li key={p} className={'page-item ' + ((p === this.state.currentPage) ? 'active' : '')}><Link id={p} onClick={this.updatePage.bind(this)} className="page-link" to={'/gyms?page=' + p}>{p}</Link></li>)}
          </ul>
        </div>
      </div>
    );
  }
}
 
export default GoogleApiWrapper({
  apiKey: ('AIzaSyCXwgr7C1qhouask5gWoOXh2Gs_n8m08Ag')
})(Gyms)