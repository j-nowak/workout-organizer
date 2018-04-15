import React, { Component } from "react";
import axios from 'axios';

class Friendships extends Component {
  constructor(props) {
    super(props);

    this.state = {
      friendshipRequests: [],
      strangers: []
    };
  }

  componentDidMount() {
    axios({
      method: 'get',
      url: 'http://localhost:9000/react/strangers',
      withCredentials: true
    })
    .then(res => {
      const strangers = res.data;
      this.setState({ strangers });
    });

    axios({
      method: 'get',
      url: 'http://localhost:9000/react/friendships',
      withCredentials: true
    })
    .then(res => {
      const friendshipRequests = res.data;
      this.setState({ friendshipRequests });
    });
  }

  render() {
    const requests = this.state.friendshipRequests;
    const suggestions = this.state.strangers;

    const requestsToShow = requests.length !== 0;
    const suggestionsToShow = suggestions.length !== 0;
    return (
      <div>
        <div>
          <h2>Friendship requests</h2>
          {requestsToShow ? 
            <div>{requests.map(fr =>
              <FriendshipRequest key={fr.id} requestId={fr.id} name={fr.firstName} />
            )}</div>
            : <div class="alert alert-success">No friendship requests.</div>}
        </div>
        <div>
          <h2>Suggested friends</h2>
          {suggestionsToShow ? 
            <div>{suggestions.map(fr =>
              <Stranger key={fr.id} requestId={fr.id} name={fr.firstName} />
            )}</div>
            : <div class="alert alert-success">No friendship suggestions.</div>}
        </div>
      </div>
    );
  }
}

export default Friendships;

class FriendshipRequest extends Component {
  constructor(props) {
    super(props);

    this.state = {
      acc: false
    }
  }

  accept() {
    axios({
      method: 'post',
      url: 'http://localhost:9000/react/users/' + this.props.requestId + '/accept',
      withCredentials: true
    })
    .then(function (response) {
        //handle success
        console.log(response);
        this.setState({ acc: true });
    }.bind(this))
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }

  decline() {
    axios({
      method: 'post',
      url: 'http://localhost:9000/react/users/' + this.props.requestId + '/decline',
      withCredentials: true
    })
    .then(function (response) {
        //handle success
        console.log(response);
        this.setState({ acc: true });
    }.bind(this))
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }

  render() {
    return (
      <div>
        {this.state.acc ? <div>Resolved!</div> :
          <div>
            {this.props.name + ' wants to be your friend.'}
            <button className="btn" onClick={() => this.accept()}>Accept</button>
            <button className="btn" onClick={() => this.decline()}>Decline</button>
          </div>
        }
      </div>
    );
  }
}

class Stranger extends Component {
  constructor(props) {
    super(props);

    this.state = {
      sent: false
    }
  }

  invite() {
    axios({
      method: 'post',
      url: 'http://localhost:9000/react/users/' + this.props.requestId + '/invite',
      withCredentials: true
    })
    .then(function (response) {
        //handle success
        console.log(response);
        this.setState({ sent: true });
    }.bind(this))
    .catch(function (response) {
        //handle error
        console.log(response);
    });
  }

  render() {
    return (
      <div>
        {this.state.sent ? <div>Sent!</div> :
          <div>
            {this.props.name + ' can be your friend!'}
            <button className="btn btn-primary" onClick={() => this.invite()}>Invite</button>
          </div>
        }
      </div>
    );
  }
}
