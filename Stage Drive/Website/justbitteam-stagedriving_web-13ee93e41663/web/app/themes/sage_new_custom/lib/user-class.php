<?php
 
class UserClass
{
  public $user_name = "nome utente";
  public $user_img = "img";

  public function __construct()
  {
      echo 'The class "', __CLASS__, '" was initiated!<br />';
  }
 
 
  public function __toString()
  {
      return $this->getProperty();
  }

  /* Set Variable */
    public function setUser($name)
    {
        $this->user = $name;
    }
 
    /* Get variable */
  public function getUser()
  {
      return $this->user;
  }

}



?>

<?php 
session_start();
if (isset($_GET['user'])){
    error_log('user set ------->');
    
    $user = $_GET['user'];
    error_log(var_dump($user,true));
    $user_obj = new UserClass;

    $user_obj->setUser($user);
    $_SESSION['user_obj'] = serialize($user_obj) ;
}
if (isset($_GET['nome'])){
    $user['firstname'] = $_GET['nome'];
    error_log('---->');
    error_log($_GET['nome']);
}
?>