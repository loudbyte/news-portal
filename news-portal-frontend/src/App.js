import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddNews from "./components/add-news.component";
import EditNews from "./components/edit-news.component";
import ViewNews from "./components/view-news.component";
import NewsList from "./components/news-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/news" className="navbar-brand">
            News Portal
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/news"} className="nav-link">
                All News
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Add
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/news"]} component={NewsList} />
            <Route exact path="/add" component={AddNews} />
            <Route path="/edit/:id" component={EditNews} />
            <Route path="/view/:id" component={ViewNews} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;