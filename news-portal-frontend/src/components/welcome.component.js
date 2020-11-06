import React, { Component } from "react";

export default class Welcome extends Component {

  render() {
    return (
      <div>
        <h1>Welcome to news portal</h1>
        <a href="/login" type="button" className="btn btn-info">Login</a>
      </div>
    );
  }
}