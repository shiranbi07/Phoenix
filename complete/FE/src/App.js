import { useState } from 'react';
import { Container, Icon, Header, Form, Segment } from 'semantic-ui-react'
import SuccessMessage from './components/messages/SuccessMessage';
import ErrorMessage from './components/messages/ErrorMessage';
import Loader from './components/loader/loader';

const App = () => {
    const [filePath, setFilePath] = useState('');
    const [results, setresults] = useState({});
    const [isLoading, setIsLoading] = useState(false);

    const apiUrl = 'http://10.189.150.30:8080/checkCode';
    const data = { filePath };

    const apiOptions = {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        },
    }
    const onSetUpdatefile = (e) => {
        setFilePath(e.currentTarget.value);
    }

    const onCodeCheckclick = async () => {
        
        setIsLoading(true);

        const res = await fetch(apiUrl, apiOptions)
            .then((response => response.json()))
            .then(data => {
                setIsLoading(false);
                setresults(data);
            })
    }

    const onClearText = () => {
        setFilePath('');
        setresults({});
    }

    return (
        <div>
            {isLoading && <Loader />}
            <Container>
                <Header textAlign='center' className='ui primary header' as='h1'>
                    <Icon.Group size='big'>
                        <Icon name='bug' color='blue'/>
                    </Icon.Group>

                    AI Code Linter
                </ Header>

                <Form>
                    <Form.Field>
                        <Form.Input label='File Path' placeholder='Enter code file path.'
                            value={filePath} onChange={onSetUpdatefile}
                        />
                        <Form.Group inline>
                            <Form.Button primary 
                                onClick={onCodeCheckclick}>
                                Check Code
                            </Form.Button>
                            <Form.Button color='lightgrey' onClick={onClearText}>
                                Clear Text
                            </Form.Button>
                        </Form.Group>
                    </Form.Field >

                    {(Object.keys(results).length > 0 ) && (
                        <>
                            <div>

                                {(results?.errorNumbers === 0) ? <SuccessMessage /> : <ErrorMessage errors={results.errorNumbers} />}

                                <div className="ui horizontal segments">

                                    <Segment id="codeResults" style={{ overflow: 'auto', minWidth: '50%', minHeight: 400 }}>
                                        <div dangerouslySetInnerHTML={{ __html: results.classResult }} />
                                    </Segment>

                                    <Segment id="remarks" style={{ overflowY: 'auto', minWidth: '50%', maxHeight: 400 }}>
                                        <div dangerouslySetInnerHTML={{ __html: results.remarks }} />
                                    </Segment>
                                </div>
                            </div>
                        </>
                    )}
                </Form>
            </Container>
        </div>

    );

};

export default App;
