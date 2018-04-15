import React, { Component } from "react";
import axios from 'axios';
import LoginForm from './LoginForm.jsx';
import RegisterForm from './RegisterForm.jsx';
import './LoginPage.css';

class LoginPage extends Component {

  render() {
    return (
      <div className="row">
        <div className="col-md-6 col-md-offset-3">
          <div id="accordion" className="panel-group" role="tablist" aria-multiselectable="false">
            <div className="panel panel-default">
              <div class="panel-heading" role="tab" id="headingLogin">
                <h5 class="panel-title">
                  <a role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseLogin" aria-expanded="true" aria-controls="collapseLogin">
                    Login to Workout Organizer
                  </a>
                </h5>
              </div>

              <div id="collapseLogin" class="panel-collapse collapse in" aria-labelledby="headingLogin">
                <div class="panel-body">
                  <LoginForm onLogin={this.props.onLoggedIn} />
                </div>
              </div>
            </div>

            <div className="panel panel-default">
              <div class="panel-heading" id="headingRegister">
                <h5 class="panel-title">
                  <a role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseRegister" aria-expanded="false" aria-controls="collapseRegister">
                    Register
                  </a>
                </h5>
              </div>

              <div id="collapseRegister" class="panel-collapse collapse" aria-labelledby="headingRegister">
                <div class="panel-body">
                  <RegisterForm onRegister={this.props.onLoggedIn} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default LoginPage;
