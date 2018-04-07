import React, { Component } from "react";
import axios from 'axios';
 
class ChangePassword extends Component {
  constructor(props) {
    super(props);
    this.state = {
      oldPassword: '',
      newPassword: '',
      repeatedPassword: ''
    }

    this.handleOldPasswordChange = this.handleChange.bind(this, 'oldPassword');
    this.handleNewPasswordChange = this.handleChange.bind(this, 'newPassword');
    this.handleRepeatedPasswordChange = this.handleChange.bind(this, 'repeatedPassword');
  }

  handleChange(field, event) {
    this.setState({[field]: event.target.value})
  }

  submitForm(event) {
    event.preventDefault();

    var bodyFormData = new FormData();
    bodyFormData.set('oldPassword', this.state.oldPassword);
    bodyFormData.set('newPassword', this.state.newPassword);
    bodyFormData.set('repeatedPassword', this.state.repeatedPassword);


    axios({
      method: 'post',
      url: 'http://localhost:9000/react/changePassword',
      data: bodyFormData,
      config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function (response) {
        //handle success
        console.log(response);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }

  render() {
    return (
      <div>
        <h2>Change password</h2>
        <form onSubmit={(e) => this.submitForm(e)}>
          <div className="form-group">
            <label htmlFor="oldPassword">Old password:</label>
            <input className="form-control" type="password" id="oldPassword" placeholder="Old password"
                value={this.state.oldPassword}
                onChange={this.handleOldPasswordChange} />
          </div>

          <div className="form-group">
            <label htmlFor="newPassword">New password:</label> 
            <input className="form-control" type="password" id="newPassword" placeholder="New password"
                value={this.state.newPassword}
                onChange={this.handleNewPasswordChange} />
          </div>

          <div className="form-group">
            <label htmlFor="repeatedPassword">Repeat password:</label>
            <input className="form-control" type="password" id="repeatedPassword" placeholder="Repeat password"
                value={this.state.repeatedPassword}
                onChange={this.handleRepeatedPasswordChange} />
          </div>

          <button type="submit" className="btn btn-primary">Submit</button>
        </form>
      </div>
    );
  }
}
 
export default ChangePassword;