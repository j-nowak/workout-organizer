import React, { Component } from "react";
import axios from 'axios';
import '../home/News.css';

class NewWorkout extends Component {
  constructor(props) {
    super(props);
    this.state = {
      availableGyms: [],
      gym: '',
      trainingDate: '',
      note: '',
      exercises: []
    }

    this.handleGymChange = this.handleChange.bind(this, 'gym');
    this.handleTrainingDateChange = this.handleChange.bind(this, 'trainingDate');
    this.handleNoteChange = this.handleChange.bind(this, 'note');
    this.handleExercisesChange = this.handleChange.bind(this, 'exercises');
  }

  handleChange(field, event) {
    this.setState({[field]: event.target.value})
  }

  submitForm(event) {
    event.preventDefault();

    var bodyFormData = new FormData();
    bodyFormData.set('gym,', this.state.gym);
    bodyFormData.set('trainingDate', this.state.trainingDate);
    bodyFormData.set('note', this.state.note);
    bodyFormData.set('exercises', this.state.exercises);

    axios({
      method: 'post',
      url: 'http://localhost:9000/react/workouts',
      data: bodyFormData,
      config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function (response) {
        //handle success
        console.log(response);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms")
      .then(res => {
        const availableGyms = res.data;
        this.setState({ availableGyms });
      });
  }

  render() {
    return (
      <div className="workoutForm">
        <h2>New workout</h2>
        <form id="new-workout" onSubmit={(e) => this.submitForm(e)}>
          <div className="form-group">
            <label htmlFor="gymId">Gym:</label>
            <select id="gymId" className="form-control">
              {this.state.availableGyms.map(g =>
                <option value={g.id} key={g.id}>{g.name}</option>)}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="startedAt">Date:</label>
            <input className="form-control" type="date" id="startedAt"
                value={this.state.trainingDate}
                onChange={this.handleTrainingDateChange} />
          </div>

          <div className="form-group">
            <label htmlFor="note">Note:</label>
            <textarea id="note" className="form-control" form="new-workout"
                value={this.state.note}
                onChange={this.handleNoteChange} />
          </div>

          <button type="submit" className="btn btn-primary">Submit</button>
        </form>
      </div>
    );
  }
}

export default NewWorkout;
