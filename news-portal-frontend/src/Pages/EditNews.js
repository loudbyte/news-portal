import React, { Component } from 'react'
import { ButtonGroup, Button, Form, Container} from 'react-bootstrap'


export default class EditNews extends Component {
    render() {
        return (
            <>
            <Form>
                <Form.Group>
                    <Form.Label>News Title</Form.Label>
                    <Form.Control type="text"/>
                </Form.Group>
                <Form.Group>
                    <Form.Label>News Date</Form.Label>
                    <Form.Control type="date"/>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Brief</Form.Label>
                    <Form.Control as="textarea" rows="4"/>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Content</Form.Label>
                    <Form.Control as="textarea" rows="4"/>
                </Form.Group>
            </Form>
            <ButtonGroup aria-label="Basic example">
                <Button href="/save" >Save</Button>
                <Button href="/newsList" variant="light">Cancel</Button>
            </ButtonGroup>  
            </>
        )
    }
}
