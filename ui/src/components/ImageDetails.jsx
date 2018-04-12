import React, { Component } from "react";
import axios from 'axios';
import './ImageDetails.css';

class ImageDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      comments: [],
      newComment: ''
    };
  }

  componentDidMount() {
    this.loadComments();

     var intervalId = setInterval(this.loadComments.bind(this), 1000);
     this.setState({updateCommentsId: intervalId});
  }

  componentWillUnmount() {
    clearInterval(this.state.updateCommentsId);
  }

  loadComments() {
    if (this.props.focusImage !== null) {
      axios.get("http://localhost:9000/react/images/" + this.props.focusImage.id + "/comments")
        .then(res => {
          const comments = res.data;
          this.setState({ comments });
        });
    }
  }

  renderComments() {
    if (this.props.focusImage === null) {
      return null;
    }

    return ( 
      <div className="comments pre-scrollable">
        {this.state.comments.map(c =>
              <div key={c.id}> {"Author: " + c.author + " - " + c.text} </div>)}
      </div>
    );
  }

  submitForm(event) {
    event.preventDefault();

    var bodyFormData = new FormData();
    bodyFormData.set('author', 'user-author');
    bodyFormData.set('newComment', this.state.newComment);

    axios({
      method: 'post',
      url: 'http://localhost:9000/react/images/' + this.props.focusImage.id + '/comments',
      data: bodyFormData,
      config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function (response) {
        var comments = this.state.comments.concat(response.data);
        this.setState({ comments, newComment: '' });
    }.bind(this))
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }

  renderCommentForm() {
    return (
      <div className="new-comment-form">
        <form onSubmit={(e) => this.submitForm(e)}>
          <div className="form-group">
            <label htmlFor="new-comment">Your comment:</label>
            <textarea id="new-comment" form="new-comment-form"
                value={this.state.newComment}
                onChange={(ev) => this.setState({ newComment: ev.target.value })} />
          </div>

          <button type="submit" className="btn btn-primary">Post a comment</button>
        </form>
      </div>
    );
  }

  render() {
    return (
      <div className="row">
          <div className="col-sm-8">
            <img
                src={this.props.focusImage.imageUrl}
                alt={this.props.focusImage.name}
            />
          </div>
          <div className="col-sm-4 comments-container">
            {this.renderComments()}
            {this.renderCommentForm()}
          </div>
      </div>
    );
  }
}
 
export default ImageDetails;