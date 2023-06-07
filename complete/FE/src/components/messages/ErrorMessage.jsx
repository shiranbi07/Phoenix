const ErrorMessage = ({errors}) => {
    return (
        <div className="ui negative message">
            <div className="header">
                Check Failed
            </div>
            <p>{`Found ${JSON.stringify(errors)} errors.`}
            </p></div>
    )
}
export default ErrorMessage;