import React, { Component } from 'react'
import { Nav } from 'react-bootstrap';

export default class Actions extends Component {
    render() {
        return (
            <div>
                <Nav variant="pills" defaultActiveKey="/home" className="flex-column">
                    <Nav.Link href="/newsList">News List</Nav.Link>
                    <Nav.Link href="/login">News List</Nav.Link>
                    <Nav.Link href="/createUpdate">News List</Nav.Link>
                </Nav>
            </div>
        )
    }
}
