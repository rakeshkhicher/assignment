i=1
a=1
for file in *.*
do
if [ $i -gt 4 ] 
then
exit
fi
echo $file
cp $file public_html
i=`expr $i + $a`
done

