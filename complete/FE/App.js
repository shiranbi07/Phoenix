
import { useEffect, useState } from 'react' import { Container,
/ import {CodeDiff} from 'react-text-diff
, Icon, Header, Form, Divider, Button, Segment, Label } from 'semantic-ui-react'
const classes = require (' . /app.module.scss');
const App = () => {
const [codeContent, setCodeContent] = usestate();
const [filePath, setFilePath] = useState('');
const [rawcode, setRawCode] = usestate(' i):
/I const apiUrb = 'http: //10.201.191.227 :8080/checkCode
const apiUr1 =
"http://10.189.150.30:8080/checkCode'
const apioptions = {
method: 'GET'
/I body: {
filePath
/ }, headers: {}
}
}
const onSetUpdatefile = (e) => {
setFilePath(e.currentTarget.value);
const onCodeCheckClick = async () =› {
const res = (await fetch(apiUrl, apioptions));
console.log(res.text ());
setRawCode(res.text ());
// .then (result =›
{
console.log (result.body);

setRawCode (JSON.stringify(result));
// onTextCodeChange(JSON.stringify(result));
// })
// .then(response =› response.json ( ))
//.then(json => {
// console. log (json);
// const { title } = json;
//}
}
}
const onClearText = () => {
setCodeContent('*);
return (
<div className={classes.wrapper}>
<Container className={classes.wrapper}>
‹Header textAlign=' center className='ui primary header' as-'h1',
‹Icon. Group size= 'big'›
‹Icon name= 'bug />
</Icon. Group>
AI Code Linter
< Header>

‹Form>
<Form. Field >
‹Form. Input label= 'Code Path' placeholder='Enter Code Path.
value={filePath} onChange={onSetUpdatefile}
/›
<Form. Group inline›
<Form. Button primary
onClick=<onCodeCheckclick}>
Chedk Code
</Form. Button>
<Form. Button color= 'teal' onClick={onClearText}>
Clear Text
</Form. Button> </Form. Group> </Form.Field>
{ (rawCode !== ' ') && (
<Form. Group >
<Form. Label label='Results' /›
< Segment style={f overflow:
'auto', minwidth: '100%', maxHeight: 300 }}>
‹div dangerouslySetInnerHTML=< { _html: rawcode }} /›
</ Segment>
</Form. Group>
)}
</Form>
</Containers>
 </div>
);
};

export default App;


