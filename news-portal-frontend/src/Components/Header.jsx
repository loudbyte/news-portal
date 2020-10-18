import React, { Component } from 'react'
import { Navbar, Nav, ButtonGroup, Container, Form, FormControl, Button } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import AddNews from '../Pages/AddNews';
import NewsList from '../Pages/NewsList';
import Welcome from '../Pages/Welcome';
import ViewNews from '../Pages/ViewNews';
import EditNews from '../Pages/EditNews';


export default class Header extends Component {
    render() {
        return (
            <>
                <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">News Portal</Navbar.Brand>
                <Nav className="mr-auto">
                <Nav.Link href="/newsList">News List</Nav.Link>
                <Nav.Link href="/addNews">Add News</Nav.Link>
                </Nav>
                <Form inline>
                <ButtonGroup aria-label="Basic example">
                    <Button variant="outline-info">En</Button>
                    <Button variant="outline-info">Ru</Button>
                </ButtonGroup>    
                </Form>
            </Navbar>

            <Router>
                <Switch>
                    <Route exact path="/addNews" component={AddNews} />
                    <Route exact path="/newsList" component={NewsList} />
                    <Route exact path="/view" component={ViewNews} />
                    <Route exact path="/edit" component={EditNews} />
                    <Route exact path="/" component={Welcome} />
                </Switch>
            </Router>
            </>
        )
    }
}
