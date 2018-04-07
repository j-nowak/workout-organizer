import React, { Component } from "react";
import { Link } from 'react-router-dom';
import axios from 'axios';
import './News.css';

class News extends Component {
  constructor(props) {
    super(props);

    this.state = {
      news: this.props.news,
    }
  }

  like() {
    axios({
      method: 'post',
      url: 'http://localhost:9000/react/workouts/' + this.state.news.workoutId + '/like',
      config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function (response) {
        var updatedNews = this.state.news;
        updatedNews.liked = true;
        updatedNews.likesCount = response.data;
        this.setState({news: updatedNews});
    }.bind(this))
    .catch(function (response) {
        //handle error
        console.log(response);
    }.bind(this));
  }

  render() {
    const news = this.state.news;
    const userUrl = "/users/" + news.friendId;
    const gymUrl = "/gyms/" + news.gymId;

    return (
      <div className="news">
        <div className="news-header">
          <Link to={userUrl} className="news-link">{news.firstName} {news.lastName + " "}</Link> 
          was doing {news.musclesNames.join(', ')} at <Link to={gymUrl} className="news-link">{news.gymName}</Link>
        </div>
        <div className="news-date">{news.starttedAt}</div>
        <div className="news-note">{news.note}</div>
        <div className="news-likes">
          Likes: <span className="news-likes-count">{news.likesCount} </span>
          {news.liked ?
            <span>Liked!</span>
            : <button className="btn btn-primary" onClick={(e) => this.like()}>Like!</button>
          }
        </div>
      </div>
    );
  }
}
 
export default News;