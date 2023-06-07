import { useState } from 'react';
import { Container, Icon, Header, Form, Segment, Label } from 'semantic-ui-react'

const App = () => {
    const [filePath, setFilePath] = useState('');
    const [rawcode, setRawCode] = useState('');
    // const apiUrl = 'http: //10.201.191.227 :8080/checkCode';
    const apiUrl = 'http://10.189.150.30:8080/checkCode';
    const apioptions = {
        method: 'POST',
        body: {
            filePath
        },
        headers: {}
    }
    const onSetUpdatefile = (e) => {
        setFilePath(e.currentTarget.value);
    }

    const onCodeCheckclick = async () => {
        const res = await fetch(apiUrl, apioptions)
        .then((response => response.text()))
        .then(data => {
            console.log(data);
            setRawCode(data);
        })
    }

    const onClearText = () => {
        setFilePath('');
    }
    return (
        <div>
            <Container>
                <Header textAlign='center' className='ui primary header' as='h1'>
                    <Icon.Group size='big'>
                        <Icon name='bug'/>
                    </Icon.Group>

                    AI Code Linter
                </ Header>

                <Form>
                    <Form.Field>
                        <Form.Input label='Code Path' placeholder='Enter Code Path.'
                            value={filePath} onChange={onSetUpdatefile}
                        />
                        <Form.Group inline>
                        <Form.Button primary
                            onClick={onCodeCheckclick}>
                            Check Code
                        </Form.Button>
                        <Form.Button color='teal' onClick={onClearText}>
                            Clear Text
                        </Form.Button>
                    </Form.Group>
                </Form.Field >

                {(rawcode !== '') && (
                    <Form.Group >
                        {/* <Form.Label label='Results' /> */}
                        <Segment style={{overflow:'auto', minwidth: '100%', maxHeight: 300}}>
                            <div dangerouslySetInnerHTML={{__html: rawcode }} />
                        </Segment>
                    </Form.Group >
                )}
            </Form>
        </Container>
 </div>

 );

};

export default App;
