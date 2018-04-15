import React, { Component } from "react";
import Workout from './Workout.jsx';
import axios from 'axios';

class Workouts extends Component {

  constructor(props) {
    super(props);
    this.state = {
      workouts: [],
    };
  }

  componentWillMount() {
    axios.get('http://localhost:9000/react/workouts', {
      withCredentials: true
    })
    .then(response => {
      this.setState({
        workouts: response.data
      });
    });
  }

  render() {
    const workouts = this.state.workouts
    return (
      <div className="page">
        <h1>Workouts</h1>
        <div className="news-feed">
          {workouts.map(workout =>
            <Workout key={workout.id} workout={workout} />
          )}
        </div>
      </div>
    );
  }
}

export default Workouts;
