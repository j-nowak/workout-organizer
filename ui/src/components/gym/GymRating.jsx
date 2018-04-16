import React, { Component } from "react";
import StarRatingComponent from 'react-star-rating-component';
import axios from 'axios';
import ProgressBar from 'react-bootstrap/lib/ProgressBar';

class GymRating extends Component {
  constructor(props) {
    super(props);

    this.state = {
      note: '',
      rating: 0,
      visible: false
    };

    this.handleNoteChange = this.handleChange.bind(this, 'note');
  }

  handleChange(field, event) {
    this.setState({[field]: event.target.value})
  }

  handleRatingChange(newValue) {
    this.setState({ rating: newValue });
  }

  submitForm(event) {
    event.preventDefault();

    this.setState({ visible: true });

    var bodyFormData = new FormData();
    bodyFormData.set('rating', this.state.rating);
    bodyFormData.set('note', this.state.note);

    console.log(this.state.rating);

    axios({
      method: 'post',
      url: 'http://localhost:9000/react/gyms/' + this.props.gymId,
      data: bodyFormData,
      config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function (response) {
      this.props.onClose();
    }.bind(this))
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }



  render() {
    return (
      <div className="gymRatingForm">
        <h2>New rating</h2>
        <ProgressBar className={this.state.visible ? "visible" : "invisible"} active now={100} />
        <form className={!this.state.visible ? "visible" : "invisible"} id="new-rating" onSubmit={(e) => this.submitForm(e)}>
          <div className="form-group">
            <label htmlFor="note">Note:</label>
            <textarea id="note" className="form-control" form="new-rating"
                value={this.state.note}
                onChange={this.handleNoteChange} />
          </div>

          <div className="form-group">
            <label htmlFor="rating">Star rating:</label>
            <StarRatingComponent
              name='rating' 
              starCount={10}
              value={this.state.rating}
              onStarClick={(nextValue, prevValue, name) => this.handleRatingChange(nextValue)}
              editing={true} />
          </div>

          <button type="submit" className="btn btn-primary">Submit</button>
        </form>
      </div>
    );
  }
}
 
export default GymRating;