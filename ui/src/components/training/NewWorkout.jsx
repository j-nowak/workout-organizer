import React, { Component } from "react";
import axios from 'axios';
import NewWorkoutEntry from './NewWorkoutEntry.jsx';
import '../home/News.css';

const formatDate = (date) =>
  date.toISOString().substr(0, 16)

class NewWorkout extends Component {
  constructor(props) {
    super(props);
    this.state = {
      availableGyms: [],
      gymId: '',
      startedAt: formatDate(new Date(Date.now() - 1000 * 60 * 60)),
      finishedAt: formatDate(new Date()),
      note: '',
      exercises: [],
      entries: [],
    }
  }

  handleChange(field, event) {
    this.setState({[field]: event.target.value})
  }

  updateWorkoutEntry(index, workoutEntry) {
    this.setState({
      entries: this.state.entries.map((e, i) => i === index ? workoutEntry : e)
    });
  }

  deleteWorkoutEntry(index) {
    this.setState({
      entries: this.state.entries.filter((e, i) => i !== index)
    });
  }

  createWorkoutEntry(event) {
    event.preventDefault();
    this.setState({
      entries: this.state.entries.concat({})
    });
  }

  submitForm(event) {
    event.preventDefault();

    const { gymId, startedAt, finishedAt, note, entries } = this.state;

    axios.post('http://localhost:9000/react/workouts', {
      gymId, startedAt, finishedAt, note, entries
    }, {
      withCredentials: true,
    })
    .then(response => {
      this.props.history.push('/workouts');
    })
    .catch(response => {
      alert(response.data);
    });
  }

  componentDidMount() {
    axios.get("http://localhost:9000/react/gyms")
    .then(res => {
      const availableGyms = res.data;
      this.setState({ availableGyms, gymId: availableGyms[0].id });
    });

    axios.get("http://localhost:9000/react/exercises")
    .then(res => {
      const exercises = res.data;
      this.setState({ exercises });
    });
  }

  render() {
    return (
      <div className="workoutForm">
        <h2>New workout</h2>
        <form id="new-workout" onSubmit={(e) => this.submitForm(e)}>
          <div className="form-group">
            <label htmlFor="gymId">Gym:</label>
            <select id="gymId" className="form-control"
                onChange={e => this.handleChange('gymId', e)}>
              {this.state.availableGyms.map(g =>
                <option value={g.id} key={g.id}>{g.name}</option>
              )}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="startedAt">Date started:</label>
            <input className="form-control" type="datetime-local" id="startedAt"
                value={this.state.startedAt}
                onChange={e => this.handleChange('startedAt', e)} />
          </div>

          <div className="form-group">
            <label htmlFor="finishedAt">Date finished:</label>
            <input className="form-control" type="datetime-local" id="finishedAt"
                value={this.state.finishedAt}
                onChange={e => this.handleChange('finishedAt', e)} />
          </div>

          <div className="form-group">
            <label htmlFor="note">Note:</label>
            <textarea id="note" className="form-control" form="new-workout"
                value={this.state.note}
                onChange={e => this.handleChange('note', e)} />
          </div>

          <ul className="workout-entries">
            {this.state.entries.map((workoutEntry, i) =>
              <NewWorkoutEntry key={i}
                workoutEntry={workoutEntry}
                exercises={this.state.exercises}
                onChange={e => this.updateWorkoutEntry(i, e)}
                onDelete={e => this.deleteWorkoutEntry(i)} />
            )}
          </ul>
          <button type="submit" className="btn btn-primary"
              onClick={e => this.createWorkoutEntry(e)}>
            Add new entry
          </button>

          <button type="submit" className="btn btn-primary">Submit</button>
        </form>
      </div>
    );
  }
}

export default NewWorkout;
