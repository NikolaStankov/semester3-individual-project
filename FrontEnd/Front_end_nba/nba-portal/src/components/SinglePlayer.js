const SinglePlayer = (props) => {
  const { id, first_name, last_name, position } = props.player;
  const { team } = props.player;

  return (
    <li>
      <div>
        {first_name} {last_name}
      </div>
    </li>
  );
};

export default SinglePlayer;
