import React, { Component } from "react";
import ExerciseRow from './ExerciseRow';
import axios from 'axios'

const targetMusclesContain = muscleName => {
  return e => e.targetMuscles.filter(m => m.muscleName === muscleName).length > 0
};

const filterFunctions = {
  all: e => true,
  evenId: e => e.id % 2 === 0,
  oddId: e => e.id % 2 === 1,
  biceps: targetMusclesContain('biceps'),
  triceps: targetMusclesContain('triceps'),
  klata: targetMusclesContain('klata'),
};

const filters = [
  { id: 'all', text: 'All' },
  { id: 'evenId', text: 'EvenId' },
  { id: 'oddId', text: 'OddId' },
  { id: 'biceps', text: 'Biceps' },
  { id: 'triceps', text: 'Triceps' },
  { id: 'klata', text: 'Klata' },
];

const sortFunctions = {
  id: (a,b) => a.id - b.id,
  name: (a,b) => a ? a.name.localeCompare(b.name) : -1
};

class Exercises extends Component {

  constructor(params) {
    super(params);

    this.state = {
      exercises: [],
      filter: 'all',
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
    if (name in filterFunctions) {
      this.setState({
        filter: name
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
      .filter(filterFunctions[this.state.filter])
      .sort(this.getSortFn());
    return (
      <div className="page">
        <h1>EXERCISES</h1>
        <div>
          {filters.map(f =>
            <button className="btn btn-default"
                disabled={f.id === this.state.filter}
                onClick={() => this.setFilterBy(f.id)}>
              {f.text}
            </button>
          )}
        </div>
        <table className="table">
          <thead>
            <tr>
              <th className={this.state.sortBy === 'id' ? 'active' : null} onClick={() => this.setSortBy('id')}>Id</th>
              <th className={this.state.sortBy === 'name' ? 'active' : null} onClick={() => this.setSortBy('name')}>Name</th>
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
