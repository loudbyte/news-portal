import React, { Component } from "react";
import NewsDataService from "../services/news.service";

export default class Login extends Component {

  constructor(props) {
    super(props);
    this.onChangeLogin = this.onChangeLogin.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onClickLogin = this.onClickLogin.bind(this);

    this.state = {
      credentionals: {
        login: "",
        password: ""
      }
    };
  }

  onChangeLogin(e) {
    const login = e.target.value;

    this.setState(prevState => ({
      credentionals: {
        ...prevState.credentionals,
        login: login
      }
    }));
  }

  onChangePassword(e) {
    const password = e.target.value;

    this.setState(prevState => ({
      credentionals: {
        ...prevState.credentionals,
        password: password
      }
    }));
  }

  onClickLogin() {
    sessionStorage.setItem('login', this.state.credentionals.login);
    sessionStorage.setItem('password', this.state.credentionals.password);
    NewsDataService.login()
    .then(response => {
      console.log(response.data);
      this.props.history.push ('/news')
    })
    .catch(e => { 
      console.log(e);
    })
  }

  render() {
    return (
      <div>
        <div class="form-group">
            <label for="exampleInputLogin">Login</label>
            <input id="login" value={this.state.credentionals.login} onChange={this.onChangeLogin} type="login" class="form-control" id="exampleInputLogin" aria-describedby="loginHelp"/>
            <small id="loginHelp" class="form-text text-muted">We'll never share your login with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input id="password" value={this.state.credentionals.password} onChange={this.onChangePassword} type="password" class="form-control" id="exampleInputPassword1"/>
        </div>
        <button onClick={this.onClickLogin} type="submit" class="btn btn-primary">Submit</button>
      </div>
    );
  }
}