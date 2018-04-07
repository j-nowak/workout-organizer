import React, { Component } from "react";
import axios from 'axios';

import News from "./News.jsx";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      requestSent: false
    };
  }

  componentDidMount() {
    window.addEventListener('scroll', this.handleOnScroll.bind(this));
    this.querySearchResult();
  }

  componentWillUnmount() {
    window.removeEventListener('scroll', this.handleOnScroll.bind(this));
  }

  querySearchResult() {
    if (!this.state.requestSent) {
      this.doQuery();
      this.setState({requestSent: true});
    }
  }

  doQuery() {
    axios({
      method: 'get',
      url: 'http://localhost:9000/react/home',
    })
    .then(function (response) {
        var newData = this.state.data.concat(response.data);
        this.setState({data: newData, requestSent: false});
    }.bind(this))
    .catch(function (response) {
        this.setState({requestSent: false});
    }.bind(this));
  }

  handleOnScroll() {
    var scrollTop = (document.documentElement && document.documentElement.scrollTop) || document.body.scrollTop;
    var scrollHeight = (document.documentElement && document.documentElement.scrollHeight) || document.body.scrollHeight;
    var clientHeight = document.documentElement.clientHeight || window.innerHeight;
    var scrolledToBottom = Math.ceil(scrollTop + clientHeight) >= scrollHeight;

    if (scrolledToBottom) {
      this.querySearchResult();
    }
  }

  render() {
    return (
      <div>
        <div className="news-feed">
          {this.state.data.map(d =>
            <News news={d} />)}
        </div>
        {(() => {
          if (this.state.requestSent) {
            return(
              <div className="data-loading">
                Loading...
              </div>
            );
          } else {
            return(
              <div className="data-loading"></div>
            );
          }
        })()}
      </div>
    );
  }
}
 
export default Home;