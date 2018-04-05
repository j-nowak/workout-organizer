import React, { Component } from "react";
import {
  Route,
  NavLink,
  HashRouter
} from "react-router-dom";
import Home from "./components/Home.jsx";
import Training from "./components/Training.jsx";
import Exercises from "./components/Exercises.jsx";
import Gyms from "./components/Gyms.jsx";
import Account from "./components/Account.jsx";

class App extends Component {
  render() {
    return (
        <HashRouter>
        <div>
          <h1>Workout organizer</h1>
          <ul className="header">
            <li><NavLink to="/">Home</NavLink></li>
            <li><NavLink to="/training">Training</NavLink></li>
            <li><NavLink to="/exercises">Exercises</NavLink></li>
            <li><NavLink to="/gyms">Gyms</NavLink></li>
            <li><NavLink to="/account">Account</NavLink></li>
            <li><NavLink to="/logout">Logout</NavLink></li>
          </ul>
          <div className="content">
            <Route exact path="/" component={Home}/>
            <Route path="/training" component={Training}/>
            <Route path="/exercises" component={Exercises}/>
            <Route path="/gyms" component={Gyms}/>
            <Route path="/account" component={Account}/>
          </div>
        </div>
      </HashRouter>
    );
  }
}
 
export default App;