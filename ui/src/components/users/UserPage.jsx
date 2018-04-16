import React, { Component } from "react";
import axios from 'axios';
import './UserPage.css'

class UserPage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      userId: props.match.params.userId
    };
  }

  componentDidMount() {
    axios.get(`http://localhost:9000/react/users/${this.state.userId}`)
    .then(res => {
      const user = res.data;
      this.setState({ user });
    });
  }

  render() {
    const user = this.state.user;
    if (!user) {
      return <div>Loading...</div>
    }

    return (
      <div className="page">
        <h1>{user.firstName} {user.lastName}</h1>
        <h3>{user.login}</h3>

        <div>
          <div>Wzrost: <span className="bold">{user.height}cm</span></div>
          <div>Waga: <span className="bold">{user.weight}kg</span></div>
        </div>
      </div>
    );
  }
}

export default UserPage;
