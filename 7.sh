mkdir temp
cp -r public_html temp
cp -r temp/public_html public_html
mv public_html/public_html public_html/pbl_htl
rm -rf temp
