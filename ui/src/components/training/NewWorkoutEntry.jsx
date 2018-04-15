import React, { Component } from "react";
import '../home/News.css';

class NewWorkoutEntry extends Component {
  constructor(props) {
    super(props);
    const workout = this.props.workout || {};
    const exercise = this.props.exercises[0] || { exerciseId: 0 }
    this.state = {
      exerciseId: workout.exerciseId || exercise.id,
      setsCount: workout.setsCount || 0,
      repsPerSet: workout.repsPerSet || 0,
      weight: workout.weight || 0,
    }
    this.props.onChange(this.state);
  }

  handleChange(field, event) {
    this.setState({ [field]: event.target.value }, () =>
      this.props.onChange(this.state)
    );
  }

  render() {
    return (
      <li className="workoutForm">
        <button type="submit" className="btn btn-danger pull-right"
            onClick={() => this.props.onDelete()}>
          Delete
        </button>
        <h4>Workout entry</h4>
        <div>
          <div className="form-group">
            <label htmlFor="exerciseId">Exercise:</label>
            <select id="exerciseId" className="form-control"
                onChange={e => this.handleChange('exerciseId', e)}>
              {this.props.exercises.map(ex =>
                <option value={ex.id} key={ex.id}>{ex.name}</option>
              )}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="setsCount">Sets count:</label>
            <input className="form-control" id="setsCount"
                type="number"
                value={this.state.setsCount}
                onChange={e => this.handleChange('setsCount', e)} />
          </div>

          <div className="form-group">
            <label htmlFor="repsPerSet">Reps count:</label>
            <input className="form-control" id="repsPerSet"
                type="number"
                value={this.state.repsPerSet}
                onChange={e => this.handleChange('repsPerSet', e)} />
          </div>

          <div className="form-group">
            <label htmlFor="weight">Weight:</label>
            <input className="form-control" id="weight"
                type="number"
                value={this.state.weight}
                onChange={e => this.handleChange('weight', e)} />
          </div>
        </div>
      </li>
    );
  }
}

export default NewWorkoutEntry;
