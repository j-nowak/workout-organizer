import React, { Component } from "react";
import axios from 'axios';

class LoginForm extends Component {

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

    axios.post('http://localhost:9000/react/login', { username, password }, { withCredentials: true })
    .then(response => {
      const user = response.data;
      this.props.onLogin(user);
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
      <form>
        <div className="form-group">
          <label htmlFor="usernameLogin">Username:</label>
          <input id="usernameLogin" className="form-control"
              type="text"
              value={this.state.username}
              onChange={e => this.updateUsername(e)} />
        </div>

        <div className="form-group">
          <label htmlFor="passwordLogin">Password:</label>
          <input id="passwordLogin" className="form-control"
              type="password"
              value={this.state.password}
              onChange={e => this.updatePassword(e)} />
        </div>

        <button type="submit" className="btn btn-primary"
            onClick={e => this.onSubmit(e)}>
          Login
        </button>
      </form>
    );
  }
}

export default LoginForm;
