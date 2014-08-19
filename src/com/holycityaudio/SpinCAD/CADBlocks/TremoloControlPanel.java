/* SpinCAD Designer - DSP Development Tool for the Spin FV-1
 * Copyright (C) 2013 - 2014 - Gary Worsham
 * Based on ElmGen by Andrew Kilpatrick.  Modified by Gary Worsham 2013 - 2014.  Look for GSW in code.
 * 
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 	
 */

package com.holycityaudio.SpinCAD.CADBlocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
class TremoloControlPanel extends JFrame implements ChangeListener, ActionListener {
	JSlider lfoSlider;
	JLabel lfoLabel;
	
	private TremoloCADBlock outBlock;
	
	public TremoloControlPanel(TremoloCADBlock tremoloCADBlock) {
		this.outBlock = tremoloCADBlock;
		this.setTitle("Tremolo");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		lfoSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		lfoSlider.addChangeListener(this);
		
		lfoLabel = new JLabel();
		
		this.getContentPane().add(lfoLabel);
		this.getContentPane().add(lfoSlider);
		
		lfoSlider.setValue((int)Math.round(30000.0 * outBlock.getLFO()));
		updateLFOLabel();		
		this.setVisible(true);
		this.pack();
		this.setLocation(outBlock.getX() + 100, outBlock.getY() + 100);
		this.setAlwaysOnTop(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void stateChanged(ChangeEvent ce) {
		if(ce.getSource() == lfoSlider) {
			outBlock.setLFO((double) lfoSlider.getValue()/30000.0);
			updateLFOLabel();
		}
	}
	
	private void updateLFOLabel() {
		lfoLabel.setText("LFO "	+ String.format("%2.2f Hz", this.outBlock.filtToFreq(outBlock.getLFO())));		
	}

}