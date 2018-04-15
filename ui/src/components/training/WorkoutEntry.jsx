import React, { Component } from "react";
import { Link } from 'react-router-dom';
// import axios from 'axios';
import './WorkoutEntry.css';

class WorkoutEntry extends Component {
  constructor(props) {
    super(props);

    this.state = {
      news: this.props.news,
    }
  }

  pluralizeSets(count) {
    return count == 1 ? '1 set' : `${count} sets`;
  }

  pluralizeReps(count) {
    return count == 1 ? '1 rep' : `${count} reps`;
  }

  render() {
    const workout = this.props.workout;
    const workoutEntries = workout.workoutEntries.concat(workout.workoutEntries);
    const gymUrl = "/gyms/" + workout.gymId;

    return (
      <div className="news">
        <div className="news-header">
          You were training at <Link to={gymUrl} className="news-link">{workout.gymName}</Link>
        </div>
        <div className="news-date">{workout.startedAt} to {workout.finishedAt}</div>
        <div className="news-note">{workout.note}</div>
        <div className="news-xxx">Exercises done:</div>
        <ul className="workout-entries">
          {workoutEntries.map(e =>
            <li key={e.exerciseId + Math.random().toString()}>
              <strong>{e.exerciseName}</strong>: {this.pluralizeSets(e.setsCount)} of {this.pluralizeReps(e.repsPerSet)} with {e.weight}kg of weight.
            </li>
          )}
        </ul>
      </div>
    );
  }
}

export default WorkoutEntry;
