import React, { Component } from "react";
import axios from 'axios';
import './LoginPage.css';

class LoginPage extends Component {

  constructor(params) {
    super(params);
    this.state = {
      username: '',
      password: '',
    };
  }

  onSubmit(event) {
    event.preventDefault()

    const { username, password } = this.state;

    axios.post('http://localhost:9000/react/login', { username, password })
    .then(response => {
      const user = response.data;
      this.props.onLoggedIn(user);
    })
    .catch(error => {
      if (error.response.status === 401) {
        alert(error.response.data);
      }
    });
  }

  updateUsername(event) {
    const username = event.target.value
    this.setState({ username });
  }

  updatePassword(event) {
    const password = event.target.value
    this.setState({ password });
  }

  render() {
    return (
      <div className="row page">
        <div className="col-md-4 col-md-offset-4">
          <h1>Login to Workout Organizer:</h1>
          <form>
            <div className="form-group">
              <label htmlFor="username">Username:</label>
              <input id="username" className="form-control"
                  type="text"
                  value={this.state.username}
                  onChange={e => this.updateUsername(e)} />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password:</label>
              <input id="password" className="form-control"
                  type="password"
                  value={this.state.password}
                  onChange={e => this.updatePassword(e)} />
            </div>

            <button type="submit" className="btn btn-primary"
                onClick={e => this.onSubmit(e)}>
              Login
            </button>
          </form>
        </div>
      </div>
    );
  }
}

export default LoginPage;
