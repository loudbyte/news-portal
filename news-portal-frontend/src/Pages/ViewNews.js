import React, { Component } from 'react'
import { Table, Button, ButtonGroup, Row, Col } from 'react-bootstrap';

export default class ViewNews extends Component {
    render() {

        return (
            <>
            <Table bordered>
                <tbody>
                    <tr>
                        <td>News Title</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>News Date</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Brief</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Content</td>
                        <td></td>
                    </tr>
                </tbody>
            </Table>
            <ButtonGroup aria-label="Basic example">
                <Button href="/edit" variant="link">Edit</Button>
                <Button href="/delete" variant="link">Delete</Button>
            </ButtonGroup>
            </>
        )
    }
}
