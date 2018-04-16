import React, { Component } from "react";

class ExerciseRow extends Component {
  render() {
    const exercise = this.props.exercise;
    return (
      <tr>
        <td>{exercise.id}</td>
        <td>{exercise.name}</td>
        <td>{exercise.targetMuscles.map(m => m.muscleName).join(', ')}</td>
      </tr>
    );
  }
}

export default ExerciseRow;
