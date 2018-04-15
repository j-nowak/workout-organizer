import React, { Component } from "react";
import {
  Route,
  NavLink,
  HashRouter
} from "react-router-dom";
import './App.css';
import Home from "./components/home/Home.jsx";
import Workouts from "./components/training/Workouts.jsx";
import NewWorkout from "./components/training/NewWorkout.jsx";
import Exercises from "./components/exercise/Exercises.jsx";
import Gyms from "./components/gym/Gyms.jsx";
import GymPage from "./components/gym/GymPage.jsx";
import ChangePassword from "./components/account/ChangePassword.jsx";
import Measurements from "./components/account/Measurements.jsx";
import Profile from "./components/account/Profile.jsx";
import UserPage from "./components/users/UserPage.jsx";
import LoginPage from './components/account/LoginPage.jsx';
import LoadingSpinner from './components/LoadingSpinner.jsx';
import { setCookie, deleteCookie, getCurrentUser } from './lib/auth';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

class App extends Component {
  constructor(params) {
    super(params);
    this.state = {
      user: null,
      isLoading: true,
    };
  }

  login(user) {
    this.setState({ user });
    setCookie(user.id);
  }

  logout() {
    this.setState({
      user: null
    });
    deleteCookie();
  }

  componentWillMount() {
    getCurrentUser()
    .then(user => {
      this.setState({ user, isLoading: false });
    })
    .catch(e => {
      this.setState({ isLoading: false });
    });
  }

  render() {
    if (this.state.isLoading) {
      return <LoadingSpinner />
    }

    const user = this.state.user;
    return (
        <HashRouter>
        <div className="App container">
          <nav className="navbar navbar-inverse">
            <ul className="nav navbar-nav">
              <li className="nav-item active"><NavLink to="/">Home</NavLink></li>

              <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Training
                  <span className="caret"></span>
                </a>
                <ul className="dropdown-menu">
                  <li className="dropdown-item"><NavLink to="/workouts" >Workouts</NavLink></li>
                  <li className="dropdown-item"><NavLink to="/workouts/new" >New workout</NavLink></li>
                </ul>
              </li>

              <li className="nav-item"><NavLink to="/exercises" >Exercises</NavLink></li>

              <li className="nav-item"><NavLink to="/gyms?page=1">Gyms</NavLink></li>

              { user ?
                <li className="nav-item dropdown">
                  <a className="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    Account
                    <span className="caret"></span>
                  </a>
                  <ul className="dropdown-menu">
                    <li className="dropdown-item"><NavLink to="/account/measurements" >Enter measurements</NavLink></li>
                    <li className="dropdown-item"><NavLink to={"/users/" + user.id} >My profile</NavLink></li>
                    <li className="dropdown-item"><NavLink to="/account/password" >Change password</NavLink></li>
                  </ul>
                </li>
                : null
              }

              { user ?
                <li className="nav-item">
                  <a className="nav-link" role="button" aria-haspopup="true"
                      onClick={() => this.logout()}>
                    Logout
                  </a>
                </li>
                : null
              }
            </ul>
          </nav>

          {user ?
            <div className="content container">
              <Route exact path="/" component={() => <Home userId={user.id}/>}/>

              <Route exact path="/workouts" component={Workouts}/>
              <Route exact path="/workouts/new" component={NewWorkout}/>

              <Route path="/exercises" component={Exercises}/>

              <Route exact path="/gyms" component={Gyms}/>
              <Route path="/gyms/:gymId" component={GymPage}/>

              <Route exact path="/account/measurements" component={Measurements}/>
              <Route exact path="/account/password" component={() => <ChangePassword userId={user.id} />}/>

              <Route path="/users/:userId" component={UserPage}/>
            </div>
            :
            <LoginPage onLoggedIn={user => this.login(user)} />
          }
        </div>
      </HashRouter>
    );
  }
}

export default App;
