import React, { Component } from "react";
import ExerciseRow from './ExerciseRow';
import axios from 'axios'

const targetMusclesContain = muscleName => {
  return e => e.targetMuscles.filter(m => m.muscleName === muscleName).length > 0
}

const filterFunctions = {
  all: e => true,
  evenId: e => e.id % 2 === 0,
  oddId: e => e.id % 2 === 1,
  biceps: targetMusclesContain('biceps'),
  triceps: targetMusclesContain('triceps'),
  klata: targetMusclesContain('klata'),
}

const sortFunctions = {
  id: (a,b) => a.id - b.id,
  name: (a,b) => a ? a.name.localeCompare(b.name) : -1
}

class Exercises extends Component {

  constructor(params) {
    super(params);

    this.state = {
      exercises: [],
      filterFn: filterFunctions['all'],
      sortBy: 'id',
      ascending: true
    }
  }

  componentDidMount() {
    axios.get('http://localhost:9000/react/exercises')
    .then(res => {
      const exercises = res.data;
      this.setState({ exercises });
    });
  }

  setFilterBy(name) {
    const fn = filterFunctions[name];
    if (fn) {
      this.setState({
        filterFn: fn
      });
    }
  }

  getSortFn() {
    const key = this.state.sortBy;
    if (this.state.ascending) {
      return sortFunctions[key];
    } else {
      return (a, b) => -sortFunctions[key](a,b);
    }
  }

  setSortBy(name) {
    console.log('setState', name, this.state.sortBy, this.state.ascending);
    if (this.state.sortBy === name) {
      this.setState({
        ascending: !this.state.ascending
      });
    } else if (name in sortFunctions) {
      this.setState({
        sortBy: name,
        ascending: true
      })
    }
  }

  render() {
    const exercises = this.state.exercises
      .filter(this.state.filterFn)
      .sort(this.getSortFn());
    return (
      <div className="page">
        <h1>EXERCISES</h1>
        <div>
          <button className="btn btn-default" onClick={() => this.setFilterBy('all')}>All</button>
          <button className="btn btn-default" onClick={() => this.setFilterBy('oddId')}>Odd ids</button>
          <button className="btn btn-default" onClick={() => this.setFilterBy('evenId')}>Even ids</button>
          <button className="btn btn-default" onClick={() => this.setFilterBy('biceps')}>Biceps</button>
          <button className="btn btn-default" onClick={() => this.setFilterBy('triceps')}>Triceps</button>
          <button className="btn btn-default" onClick={() => this.setFilterBy('klata')}>Klata</button>
        </div>
        <table className="table">
          <thead>
            <tr>
              <th onClick={() => this.setSortBy('id')}>Id</th>
              <th onClick={() => this.setSortBy('name')}>Name</th>
              <th>Target muscles</th>
            </tr>
          </thead>
          <tbody>
            {exercises.map(exercise =>
              <ExerciseRow key={exercise.id} exercise={exercise} />
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default Exercises;
