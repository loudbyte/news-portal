import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddNews from "./components/add-news.component";
import EditNews from "./components/edit-news.component";
import ViewNews from "./components/view-news.component";
import NewsList from "./components/news-list.component";
import Login from "./components/login.component";
import Welcome from "./components/welcome.component";
import NewsDataService from "./services/news.service";



class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/" className="navbar-brand">
            News Portal
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <a href="/news" className="nav-link">
              All News
              </a>
            </li>
            <li className="nav-item">
              <a href="/newsen" className="nav-link">
              EN News
              </a>
            </li>
            <li className="nav-item">
              <a href="/newsru" className="nav-link">
              RU News
              </a>
            </li>
            <li className="nav-item">
              <a href="/add" className="nav-link">
               Add
              </a>
            </li>
          </div>
          <div className="navbar-nav mr">
          <li className="nav-item">
              <Link onClick={NewsDataService.logout} className="nav-link">
                Logout
              </Link>
            </li>
          </div>

        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/"]} component={Welcome} />
            <Route exact path={["/login"]} component={Login} />
            <Route exact path={["/news"]} component={NewsList} />
            <Route exact path="/add" component={AddNews} />
            <Route path="/edit/:id" component={EditNews} />
            <Route path="/view/:id" component={ViewNews} />
            <Route path="/news:lang" render={(props) => <NewsList {...props} />}/> 
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;