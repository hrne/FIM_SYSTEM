<?PHP
    $message = $_GET['message'];

    $url = "http://192.168.0.105/send?message=$message";
         
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    $output = curl_exec($ch);
    curl_close($ch);    
    echo $output;
    
?>