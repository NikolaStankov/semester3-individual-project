import LoginForm from "../components/LoginForm";

const Login = (props) => {
  return (
    <LoginForm
      updateLoggedUserProps={props.updateLoggedUserProps}
      updateExpirationDateProps={props.updateExpirationDateProps}
    />
  );
};

export default Login;
