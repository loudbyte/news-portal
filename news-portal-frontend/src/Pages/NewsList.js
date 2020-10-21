import React, {Component} from 'react';
import {Form, Card, Col, Row, ListGroup, Button, ButtonGroup, Table} from 'react-bootstrap';

class NewsList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoaded: false,
        }

    }

    componentDidMount() {
        fetch('http://localhost:8080/news-portal/api/news')
        // fetch('https://jsonplaceholder.typicode.com/users')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    items: json,
                })
            })
    }

    getParsedDate(strDate){
        var strSplitDate = String(strDate).split(',');
        var date = new Date(strSplitDate[0]);
        var dd = date.getDate();
        var mm = date.getMonth() + 1; //January is 0!
    
        var yyyy = date.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        date =  dd + "-" + mm + "-" + yyyy;
        return date.toString();
    }


    render() {

        var { isLoaded, items } = this.state;

        if (!isLoaded) {
            return <div>Loading...</div>;
        }

        else {
            return (
                <div>
                      <ListGroup variant="flush">

                    {items.map(item => (
                        <ListGroup.Item>
                            <Card  align="left">
                            <Card.Header>
                                <Row>
                                    <Col>
                                        {item.title}
                                    </Col>
                                    <Col xs={4}>
                                        {this.getParsedDate(item.newsDate)}
                                    </Col>
                                </Row>
                            </Card.Header>    
                            <Card.Body>
                                <Card.Text>
                                {item.content}
                                </Card.Text>
                                <Form>
                                    <ButtonGroup aria-label="Basic example">
                                        <Button href="/view" variant="link">View</Button>
                                        <Button href="/edit" variant="link">Edit</Button>
                                        <Form.Check aria-label="option 1" />
                                    </ButtonGroup>  
                                </Form>
                            </Card.Body>
                            </Card>

                             {/* <Table>
                                 <thead>
                                 <tr>
                                     <th>{item.title}</th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <tr>
                                     <td>
                                         {item.content}
                                     </td>
                                 </tr>
                                 </tbody>
                             </Table> */}
                        </ListGroup.Item>
                        ))}
                      </ListGroup>
                      <Button>Delete</Button>
                </div>
            );
        }
    }
}

export default NewsList;