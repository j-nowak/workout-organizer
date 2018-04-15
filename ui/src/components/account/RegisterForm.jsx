import React, { Component } from "react";
import axios from 'axios';

class RegisterForm extends Component {

  constructor(params) {
    super(params);
    this.state = {
      username: '',
      email: '',
      firstName: '',
      lastName: '',
      password: '',
      passwordConfirm: '',
    };
  }

  validateForm() {
    const { username, password, passwordConfirm } = this.state;

    if (!username || username.length < 3 || username.length > 60) {
      alert('Username incorrect!');
      return false;
    }

    if (password !== passwordConfirm) {
      alert('Password confirmation mismatch!');
      return false;
    }

    return true;
  }

  onSubmit(event) {
    event.preventDefault()

    if (!this.validateForm()) {
      return;
    }

    const { username, password, passwordConfirm, firstName, lastName, email } = this.state;

    axios.post('http://localhost:9000/react/register', {
      username, password, passwordConfirm, firstName, lastName, email
    })
    .then(response => {
      const user = response.data;
      this.props.onRegister(user);
    })
    .catch(error => {
      if (error.response && error.response.status === 400) {
        alert(error.response.data);
      }
    });
  }

  updateField(fieldName, event) {
    const value = event.target.value
    this.setState({ [fieldName]: value });
  }

  render() {
    return (
      <form>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input id="username" className="form-control"
              type="text"
              value={this.state.username}
              onChange={e => this.updateField('username', e)} />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input id="email" className="form-control"
              type="email"
              value={this.state.email}
              onChange={e => this.updateField('email', e)} />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input id="password" className="form-control"
              type="password"
              value={this.state.password}
              onChange={e => this.updateField('password', e)} />
        </div>

        <div className="form-group">
          <label htmlFor="password">Confirm password:</label>
          <input id="password" className="form-control"
              type="password"
              value={this.state.passwordConfirm}
              onChange={e => this.updateField('passwordConfirm', e)} />
        </div>

        <div className="form-group">
          <label htmlFor="firstName">First name:</label>
          <input id="firstName" className="form-control"
              type="text"
              value={this.state.firstName}
              onChange={e => this.updateField('firstName', e)} />
        </div>

        <div className="form-group">
          <label htmlFor="lastName">Last name:</label>
          <input id="lastName" className="form-control"
              type="text"
              value={this.state.lastName}
              onChange={e => this.updateField('lastName', e)} />
        </div>

        <button type="submit" className="btn btn-primary"
            onClick={e => this.onSubmit(e)}>
          Register
        </button>
      </form>
    );
  }
}

export default RegisterForm;
