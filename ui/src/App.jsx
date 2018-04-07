import React, { Component } from "react";
import {
  Route,
  NavLink,
  HashRouter
} from "react-router-dom";
import Home from "./components/Home.jsx";
import Workouts from "./components/training/Workouts.jsx";
import NewWorkout from "./components/training/NewWorkout.jsx";
import Exercises from "./components/exercise/Exercises.jsx";
import Gyms from "./components/gym/Gyms.jsx";
import ChangePassword from "./components/account/ChangePassword.jsx";
import Measurements from "./components/account/Measurements.jsx";
import Profile from "./components/account/Profile.jsx";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

class App extends Component {
  render() {
    return (
        <HashRouter>
        <div className="App">
          <h1>Workout organizer</h1>

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

              <li className="nav-item"><NavLink to="/gyms">Gyms</NavLink></li>

              <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                  Account
                  <span className="caret"></span>
                </a>
                <ul className="dropdown-menu">
                  <li className="dropdown-item"><NavLink to="/account/measurements" >Enter measurements</NavLink></li>
                  <li className="dropdown-item"><NavLink to="/account/profile" >Edit profile</NavLink></li>
                  <li className="dropdown-item"><NavLink to="/account/password" >Change password</NavLink></li>
                </ul>
              </li>


              <li className="nav-item"><NavLink to="/logout">Logout</NavLink></li>
            </ul>
          </nav>

          <div className="content">
            <Route exact path="/" component={Home}/>

            <Route exact path="/workouts" component={Workouts}/>
            <Route exact path="/workouts/new" component={NewWorkout}/>

            <Route path="/exercises" component={Exercises}/>

            <Route path="/gyms" component={Gyms}/>

            <Route exact path="/account/measurements" component={Measurements}/>
            <Route exact path="/account/profile" component={Profile}/>
            <Route exact path="/account/password" component={ChangePassword}/>
          </div>
        </div>
      </HashRouter>
    );
  }
}
 
export default App;