import React, { Component } from "react";
import NewsDataService from "../services/news.service";

export default class Login extends Component {

  render() {
    return (
      <div>
        <form action="login" method="POST">
        <div class="form-group">
            <label for="exampleInputLogin">Login</label>
            <input type="login" class="form-control" id="exampleInputLogin" aria-describedby="loginHelp"/>
            <small id="loginHelp" class="form-text text-muted">We'll never share your login with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1"/>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    );
  }
}