var x_const;
var y_const;
var const_val;

function parse1()
{
var form =document.getElementById("myform");
var eq=document.getElementById("equation").value;
var patt =new RegExp("[a-z0-9]+[x][+|-][a-z0-9]+[y][+|-][a-z0-9]+[=][0]");

if(patt.test(eq)==true)
       alert("all ok");
else
       alert("enter correct equation");  

var a=eq.indexOf('x');
 x_const=eq.substring(0, a);
document.getElementById("x_const").value=x_const;

var b=eq.indexOf('y');
 y_const=eq.substring(a + 2, b);
document.getElementById("y_const").value=y_const;

var c=eq.indexOf('=');
 const_val=eq.substring(b + 2, c);
document.getElementById("const_value").value=const_val;

}

function graph_draw()
{
var x_min = parseInt(prompt("enter min value of x"));
var x_max = parseInt(prompt("enter max value of x"));
var step = parseInt(prompt("enter step value")); 
document.getElementById("x_min").value=x_min;
document.getElementById("x_max").value=x_max;
document.getElementById("step").value=step;  
 
x_const=parseInt(document.getElementById("x_const").value);
y_const=parseInt(document.getElementById("y_const").value);
const_val=parseInt(document.getElementById("const_value").value);

var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
context.beginPath();
context.moveTo(250,250);
var i;
var y;
for (i = x_min; i <= x_max;i = i + step)
{
      y = parseInt((-(const_val) - (i * x_const)) / (y_const));
           //alert(y);
    
      context.lineTo(i,y);
      context.stroke();       
}  
                     
}



