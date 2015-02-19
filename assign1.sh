1]
hostname>assign.log
uname -o && uname --version>>assign.log
echo ~>>assign.log
who -H>>assign.log
groups>>assign.log
find $HOME -type f>>assign.log
date>>assign.log

2]
rename 's/\.txt$/_.txt/' *
ls

3]
rename 's//a_/' x*
ls

4]
date
sh 2.sh>>assignment.log
sh 3.sh>>assignment.log

5]
mkdir public_html
cd public_html
touch readme.txt
chmod 0777 readme.txt

6]
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

7]
mkdir temp
cp -r public_html temp
cp -r temp/public_html public_html
mv public_html/public_html public_html/pbl_htl
rm -rf temp
